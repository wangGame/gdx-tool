#ifdef GL_ES
precision highp float;
#endif


varying vec2 v_textCoords;
uniform sampler2D u_texture;
varying vec4 v_color;
uniform float widthOfset = 1;
uniform float heightOfset = 1;
uniform float gaussianWeights[961];
uniform int blurRadius = 1;

void main() {
    if(blurRadius == 0){
        discard;
       // gl_FragColor = texture2D(u_texture,v_textCoords);
    }else{
        vec2 offset = vec2(widthOfset,heightOfset);
        vec4 sum = vec4(0.0);
        int x = 0;
         for (int i = -blurRadius; i <= blurRadius; i++) {
            for (int j = -blurRadius; j <= blurRadius; j++) {
                float weight = gaussianWeights[x];
                sum += (texture2D(u_texture, v_textCoords+offset*vec2(i,j))*weight);
                x++;
            }
        }


        gl_FragColor = sum * v_color;

    }


}