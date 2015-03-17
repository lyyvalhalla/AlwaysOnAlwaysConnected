/*
x range: (-20, 20);
y range: (-20, 20);
z range: (-20, 20);
*/





import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import org.json.JSONObject;


public SocketIO socket;
float xx, y, z; 
JSONObject json;
boolean connect = false;

void setup()
{
  try{
      socket = new SocketIO("http://ec2-54-86-1-80.compute-1.amazonaws.com:8080");
      socket.connect(new IOCallback() {
         @Override
         public void on(String event, IOAcknowledge ack, Object... args) {
// for every event args[0] equals,
    
           
                if (event.equals("otherevent")) {
                    println("SocketIO", "otherevent " + args[0]);
                } else if (event.equals("Accelx")) {      
                    println("Accelx: " + args[0]);
                    xx =Float.valueOf(String.valueOf(args[0]));
                }
                   else if(event.equals("Accely")) {
                      println("Accely: " + args[0]);
                      y = Float.valueOf(String.valueOf(args[0]));
                   }
                     else if(event.equals("Accelz")) {
                        println( "Accelz: " + args[0]);
                        z = Float.valueOf(String.valueOf(args[0]));
                     }
               
                      else {
                        println("SocketIO", event + " " + args[0]);
                        }
            }
    
            @Override
            public void onMessage(JSONObject json, IOAcknowledge ack) {
                println("SocketIO", json.toString());
                
            }
            @Override
            public void onMessage(String data, IOAcknowledge ack) {
                println("SocketIO", data);
                
              }
            @Override
            public void onError(SocketIOException socketIOException) {
                socketIOException.printStackTrace();
            }
            @Override
            public void onDisconnect() {
                println("SocketIO", "Disconnected");          
            }
            @Override
            public void onConnect() {
                println("SocketIO", "Connected");
                connect = true;

            }    
        });
        socket.emit("message", "processing says hello");  
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      size(800, 800);
     
      strokeWeight(0);
      smooth();
      
//      aa = args;
}


float v =0;
float w =0;

void draw() {
  
  fill(0,50);
  rect(0,0,width,height);
 
  noFill();
  
  for (int i =0; i < 20; i++) {
    stroke(255, 255, 255, i*50);
    beginShape();
    for(float x = 0; x < width; x += 1) {
    vertex(x, random(height/2-5, height/2)+  i*10
    *sin(v+(xx/map(x,-20,20,1,100)))
    *tan(w+(x/map(y,-20,20,100,400)))
    *sin(v+(x/map(z,-20,20,1,100)))
    );
  }
  endShape();
    
  }
  
  
  for (int i =0; i < 10; i++) {
    stroke(0, i*20);
    beginShape();
    for(float x = 0; x < width; x += 1) {
    vertex(x, random(height/2-5, height/2)+  i*10
    *sin(v+(xx/map(x,-20,20,1,100)))
    *tan(w+(x/map(y,-20,20,100,400)))
    *sin(v+(x/map(z,-20,20,1,100)))
    );
  }
  endShape();
    
  }
  
  v -= 0.02;
  w += 0.04;
  
}
