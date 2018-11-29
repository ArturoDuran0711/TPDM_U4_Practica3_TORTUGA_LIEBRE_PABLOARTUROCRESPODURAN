package mx.edu.ittepic.tpdm_u4_practica3_fabulaprogramada;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button iniciar;
    TextView cronologia,marcadorTortuga,marcadorLiebre;
    String mensaje;
    int avanceTortuga=0,avanceLiebre=0, numero;
    boolean estado,estadoTortuga,estadoLiebre;
    Thread hilo,liebre,tortuga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar = findViewById(R.id.iniciar);
        cronologia = findViewById(R.id.cronologia);
        marcadorLiebre = findViewById(R.id.marcadorLiebre);
        marcadorTortuga = findViewById(R.id.marcadorTortuga);



        //
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensaje="";
                mensaje="LA CARRERA HA INICIADO";
                cronologia.setText(mensaje);

                iniciar.setEnabled(false);
                estado=true;
                estadoLiebre=true;
                estadoTortuga=true;

                //HILO CARRERA
                hilo = new Thread(){
                    public void run(){
                        while (estado){

                            //genera numero aleatorio
                            numero = (int) (Math.random() * 100) + 1;

                            //INICIA HILO DE TORTUGA
                            tortuga=new Thread(){
                                public void run(){
                                    if(numero>=1 &&numero<=49){
                                        avanceTortuga=avanceTortuga+3;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mensaje="Tortuga avanza 3 su posicion es: "+avanceTortuga+"\n"+mensaje;
                                                cronologia.setText(mensaje);
                                                marcadorTortuga.setText("TORTUGA: "+avanceTortuga);
                                            }
                                        });
                                    }
                                    if(numero>=50 &&numero<=69){
                                        int n =avanceTortuga-6;
                                        if(n<=1){
                                            avanceTortuga=1;
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mensaje="Tortuga retrocede 6 su posicion es: "+avanceTortuga+"\n"+mensaje;
                                                    cronologia.setText(mensaje);
                                                    marcadorTortuga.setText("TORTUGA: "+avanceTortuga);
                                                }
                                            });

                                        }else {
                                            avanceTortuga=avanceTortuga-6;
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mensaje="Tortuga retrocede 6 su posicion es: "+avanceTortuga+"\n"+mensaje;
                                                    cronologia.setText(mensaje);
                                                    marcadorTortuga.setText("TORTUGA: "+avanceTortuga);
                                                }
                                            });
                                        }
                                    }
                                    if(numero>=70){
                                        avanceTortuga=avanceTortuga+1;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mensaje="Tortuga avanza 1 su posicion es: "+avanceTortuga+"\n"+mensaje;
                                                cronologia.setText(mensaje);
                                                marcadorTortuga.setText("TORTUGA: "+avanceTortuga);
                                            }
                                        });
                                    }
                                    if(avanceTortuga>=70){
                                        estado=false;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                                alerta.setTitle("TENEMOS GANADOR")
                                                        .setMessage("El ganador es la TORTUGA")
                                                        .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                iniciar.setText("REINICIAR");
                                                                iniciar.setEnabled(true);
                                                                avanceLiebre=0;
                                                                avanceTortuga=0;
                                                            }
                                                        })
                                                        .show();
                                            }
                                        });


                                    }

                                    //SLEEP DE LA TORTUGA
                                    try {
                                        sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            //TERMINA HILO DE TORTUGA

                            //INICIA HILO DE LIEBRE
                            liebre=new Thread(){
                                public void run(){
                                    if(numero>=1 &&numero<=19){
                                        try {
                                            sleep(500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if(numero>=20 &&numero<=29){
                                        int n =avanceLiebre-16;
                                        if(n<=1){
                                            avanceLiebre=1;
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mensaje="Liebre retrocede 12 su posicion es: "+avanceLiebre+"\n"+mensaje;
                                                    cronologia.setText(mensaje);
                                                    marcadorLiebre.setText("LIEBRE: "+avanceLiebre);
                                                }
                                            });

                                        }else {
                                            avanceLiebre=avanceLiebre-12;
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mensaje="Liebre retrocede 12 su posicion es: "+avanceLiebre+"\n"+mensaje;
                                                    cronologia.setText(mensaje);
                                                    marcadorLiebre.setText("LIEBRE: "+avanceLiebre);
                                                }
                                            });
                                        }
                                    }
                                    if(numero>=30 &&numero<=49){
                                        avanceLiebre=avanceLiebre+9;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mensaje="Liebre avanza 9 su posicion es: "+avanceLiebre+"\n"+mensaje;
                                                cronologia.setText(mensaje);
                                                marcadorLiebre.setText("LIEBRE: "+avanceLiebre);
                                            }
                                        });
                                    }
                                    if(numero>=50 &&numero<=69){
                                        avanceLiebre=avanceLiebre+1;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mensaje="Liebre avanza 1 su posicion es: "+avanceLiebre+"\n"+mensaje;
                                                cronologia.setText(mensaje);
                                                marcadorLiebre.setText("LIEBRE: "+avanceLiebre);
                                            }
                                        });
                                    }
                                    if(numero>=70){
                                        int n =avanceLiebre-2;
                                        if(n<=1){
                                            avanceLiebre=1;
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mensaje="Liebre retrocede 2 su posicion es: "+avanceLiebre+"\n"+mensaje;
                                                    cronologia.setText(mensaje);
                                                    marcadorLiebre.setText("LIEBRE: "+avanceLiebre);
                                                }
                                            });

                                        }else {
                                            avanceLiebre=avanceLiebre-2;
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mensaje="Liebre retrocede 2 su posicion es: "+avanceLiebre+"\n"+mensaje;
                                                    cronologia.setText(mensaje);
                                                    marcadorLiebre.setText("LIEBRE: "+avanceLiebre);
                                                }
                                            });
                                        }
                                    }
                                    if(avanceLiebre>=70){
                                        estado=false;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                                alerta.setTitle("TENEMOS GANADOR")
                                                        .setMessage("El ganador es la LIEBRE")
                                                        .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                iniciar.setText("REINICIAR CARRERA");
                                                                iniciar.setEnabled(true);
                                                                avanceLiebre=0;
                                                                avanceTortuga=0;
                                                            }
                                                        })
                                                        .show();
                                            }
                                        });


                                    }

                                    //SLEEP DE LA LIEBRE
                                    try {
                                        sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            //TERMINA HILO DE LIEBRE



                            //INICIO HILO DE TORTUGA
                            tortuga.start();
                            //INICIO HILO DE LIEBRE
                            liebre.start();
                            //SLEEP DE LA CARRERA
                            try {
                                sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                // HILO CARRERA



                hilo.start();

            }
        });

    }
}
