package com.example.projectionviewportcamera;

import java.awt.event.KeyEvent;
import java.io.File;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.Vector3;

public class ProjectionViewportCamera implements ApplicationListener {
    private Mesh mesh;
    private Mesh mesh2;
    private Camera camera;
    Music music;
    int m=0;
    
    int oddaljenost=3;
    static float z=3;
    static Vector3 position = new Vector3(0, 0, z);

    @Override
    public void create() {
    	
    	if (mesh == null) {
            mesh = new Mesh(true, 3, 3, 
                    new VertexAttribute(Usage.Position, 3, "a_position"));          

            mesh.setVertices(new float[] { -0.5f, -0.5f, 0,
                                           0.5f, -0.5f, 0,
                                           0, 0.5f, 0 });   
            mesh.setIndices(new short[] { 0, 1, 2 });                       
    }
    	
    	if (mesh2 == null) {
            mesh2 = new Mesh(true, 3, 3, 
                    new VertexAttribute(Usage.Position, 3, "a_position"));          

            mesh2.setVertices(new float[] { -1.0f, -1.0f, 0,
                                           1.0f, 0.5f, 0,
                                           0, 1.5f, 0 } );   
            mesh2.setIndices(new short[] { 0, 1, 2 });
            
    }	
    }
    
    public void key(KeyEvent e, String keyStatus){
    	int id = e.getID();
        String keyString;
        if (KeyEvent.KEY_TYPED == KeyEvent.VK_RIGHT){
        	camera.translate(1, 0, 2);
        	camera.update();
        }        	
    }
    
    public void keyPressed(KeyEvent e) {
        
    }
        
    @Override
    public void dispose() { 
    	music.dispose();
    	
    }

    @Override
    public void pause() { }

    private int total = 0;
    private float movementIncrement = 0.0006f;

    @Override
    public void render() {
    	
        total += 1;
        if (total > 500) {
            movementIncrement = -movementIncrement;
            total = -200;
        }
        int i=0;
        
        
        
        if(Gdx.input.isKeyPressed(Keys.KEYCODE_DPAD_RIGHT)) {
        	i++;
        	camera.rotate(i, 0, 1, 0);
        	
        }
        	
        if(Gdx.input.isKeyPressed(Keys.KEYCODE_DPAD_LEFT)) {
        	i--;
        	camera.rotate(i, 0, 1, 0);
        }
        if(Gdx.input.isKeyPressed(Keys.KEYCODE_DPAD_UP)) {
        	z=z+0.08f;
        	camera.position.set(0,0,z);
        }
        if(Gdx.input.isKeyPressed(Keys.KEYCODE_DPAD_DOWN)) {
        	z=z-0.08f;
        	camera.position.set(0,0,z);
        }
        if(Gdx.input.isKeyPressed(Keys.KEYCODE_P)) {
        	 spilaj();
        	 m=1;
        }
        
        if(Gdx.input.isKeyPressed(Keys.KEYCODE_O)){
        		pavza();
        }
        //lookat
       
 
        //camera.translate(i, 0, i);
        camera.update();
        camera.apply(Gdx.gl10);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        mesh.render(GL10.GL_TRIANGLES, 0, 4);
        //mesh2.render(GL10.GL_TRIANGLES, 0, 4);
          
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f);
        //camera = new OrthographicCamera(67, 2f * aspectRatio);
        Audio a = new Audio() {
			
			@Override
			public Sound newSound(FileHandle fileHandle) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Music newMusic(FileHandle file) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public AudioRecorder newAudioRecoder(int samplingRate, boolean isMono) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public AudioDevice newAudioDevice(boolean isMono) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		/*FileHandle b;
		b = "";
		a.newMusic(asd.);*/
		
		
    }
    
    public void spilaj(){
    	if (m==0)
    		music = Gdx.audio.newMusic(Gdx.files.absolute("C:\\StariZenin.mp3"));
    	music.play();
    	
    	
    }
    
    public void pavza(){
    	music.pause();
    }

    @Override
    public void resume() { }
    
    FileHandle asd = new FileHandle() {
		
		@Override
		public FileHandle parent() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public FileHandle child(String name) {
			// TODO Auto-generated method stub
			return null;
		}
	};
}

