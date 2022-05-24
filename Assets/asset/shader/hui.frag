#ifdef GL_ES
precision mediump float;
#endif


//input from vertex shader
varying vec4 v_color;
varying vec2 v_textCoords;
uniform sampler2D u_texture;


void main() {
//    vec4 tempColor = v_color* texture2D(u_texture,v_textCoords);
//       if(tempColor.a>0.1){
//           gl_FragColor = vec4(0.3, 0.3, 0.3, tempColor.a);
//       }else{
//           gl_FragColor = vec4(1, 1, 1, tempColor.a);
 //      }
    vec4 tempColor = v_color* texture2D(u_texture,v_textCoords);
    float c=(tempColor.r + tempColor.g + tempColor.b)/3.0;
    gl_FragColor = vec4(c, c, c, tempColor.a);



}