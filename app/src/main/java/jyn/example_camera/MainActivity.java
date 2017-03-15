package jyn.example_camera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/*
* 간단한 카메라 예제*
* */

public class MainActivity extends Activity implements SurfaceHolder.Callback{
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView sfvCamera_main = (SurfaceView)findViewById(R.id.sfvCamera_main); //카메라 내용을 표시하기위한 화면
        SurfaceHolder sfvHolder = sfvCamera_main.getHolder(); //화면에 표시하기위한 정보를 저장하는 홀더
        sfvHolder.addCallback(this); //홀더콜백정보 연결
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            //SurfaceView가 만들어지지 않은상태이기 때문에.
            //액티비티의 onStart 나 onResume 에서 실행해도 카메라 연결이 안된다.
            mCamera = Camera.open(); //카메라 기능오픈
            mCamera.setDisplayOrientation(90); //세로로 보기위하여 화면을 보기위하여 90도 회전
            mCamera.setPreviewDisplay(surfaceHolder); //카메라 정보를 전달하기위한 홀더연결
            mCamera.startPreview(); //카메라 시작.
        } catch (IOException e) {

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //SurfaceView가 소멸되면 현재 사용중인 카메라 자원도 같이 제거해준다.
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }
}
