
package org.cgi.poc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/*
 * LA PARTIE GRAPHIQUE DE L'ACTIVITE QUI GERE LA CEMERA  AVEC LA DEFINITION DES DES COMPOSANT DE LA VUE DANS LE XML
 * TEMPLATE =  template_fragment_data_camera
 * CETTE ACTIVITE SERA LANCE PAR LE BOUTON IMAGE DEPUIS LE FORMULAIRE DE CREATION DE LA DATA 
 */
@SuppressWarnings("deprecation")
public class DataCameraFragement extends Fragment {
      /*
       * TAG QUI PERMET DE SUIVRE LES LOG LORS DE L'EXECUTION DE SON ACTIVITE
       */
	  private static final String TAG = "DataCameraFragment"; 
	  /*
	   * UNE CLE QUI PERMET DE PASSER LE NOM DE LA PHOTO CREE D'UNE ACTIVITE A UNE AUTRE 
	   */
	  public static final String EXTRA_PHOTO_NOM_PHOTO = "org.cgi.poc.cgipoc.nomfichier";
      /*
       * CAMERA EST L'OBJET QUI PERMET DE PRENDRE DE LA VIDEO OU PHOTO   PERMET DE MANIPULER L'APPAREIL DU SMARTPHONE
       */
	  Camera camera; 
	  /*
	   * UN OBJET DE PREVISUALISATION QUI PERMET DE PREVISUALISER UN IMAGE PAR EXEMPLE
	   * IL EST CONNECTE A UNE IMAGE OU UNE VIDEO 
	   */
	  SurfaceView maSurfaceDeVue; 
	
      /*
       * BARRE DE CHARGE LORS DE PRENDRE LA PHOTO MONTRER QUE L'APPAREIL EST EN TRAIN DE PRENDRE LA PHOTO
       */
	  private View barreDeChargeContainer; 
   
	  /*
	   * PRISE DE PHOTO AVEC ANDROID PASSE DES ETAPE ET CHAQUE ETAPE EST CARACTERISEE PAR SES INTERFACE QUI PERMET DE REAGIR
	   * - BOUTON PRENDRE PHOTO ACTIONNE MAIS LA PHOTO N EST PAS ENCORE DISPONIBLE   INTERFACE = SutterCallback    
	   * - PHOTO DISPOBIBLE MAIS DANS UN ETAT BINAIRE    INTERFACE = PictureCallback   ARG1 
	   * - PHOTO DISPONIBLE DANS LE FORMAT JPEG     INTERFACE =   PictureCallback   ARG2
	   * --> CES INTERFACE PEUVENT ETRE IMPLEMENTEES ET PASSE EN PARAMETRES A LA METHODE tackePicture 
	   */
	  
	  private Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
		   public void onShutter() {
			   /*
			    * A CE NIVEAU LA PHOTO N EST PAS ENCORE PREPAREE ALORS ACTIONNER LA BARE DE CHARGE  
			    */
			   barreDeChargeContainer.setVisibility(View.VISIBLE); 
		   }
	  }; 
	  
	  private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				/*
				 * CREER UN FICHIER 
				 */
				String nomImage = UUID.randomUUID().toString()+"jpg"; 
				/*
				 * ENREGISTRER L'IMAGE SUR LE DISQUE 
				 */
				FileOutputStream outputStream = null; 
				boolean imageReussie = true; 
				try {
					outputStream = getActivity().openFileOutput(nomImage, Context.MODE_PRIVATE); 
					outputStream.write(data); 
				}catch(IOException ex) {
					Log.e(TAG, "ERREUR DANS LE CREATION DE LA PHOTO ", ex); 
					imageReussie = false; 
				}finally {
					try {
						if(outputStream != null) 
							outputStream.close(); 
					}catch(IOException e) {
						Log.e(TAG, "ERREUR DANS LA CLOTURE DU FICHIER ", e); 
					}
				}
				
				if(imageReussie) {
					Log.e(TAG, "Prise de la photo Reussie  fichier = "+nomImage);
					Intent i = new Intent(); 
					i.putExtra(EXTRA_PHOTO_NOM_PHOTO, nomImage); 
					getActivity().setResult(Activity.RESULT_OK, i); 
				}else {
					getActivity().setResult(Activity.RESULT_CANCELED); 
				}
				getActivity().finish();  
			}
	};
	  
	/*
	 * METHODE DE LA CLASSE FRAGEMENT A IMPLEMENTER POUR CREER LA VUE 
	 */
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
	       /*
	        * FORMATER  LA VUE DE CE FRAGEMENT AVEC LE TEMPLATE template_fragment_data_camera 
	        */
		   View v = inflater.inflate(R.layout.template_fragment_data_camera, parent, false); 
	       
		   /*
		    * RECUPERRATION DE LA BARE DU CONTENEUR DE LA BARRE DE CHARGE
		    * TANT QUE LE BOUTON PRENDRE PHOTO N EST ACTIONNE IL FAUT CACHER LA BARRE DE CHARGE 	 
		    */
		   barreDeChargeContainer = v.findViewById(R.id.data_camera_bare_de_charge_container);
		   barreDeChargeContainer.setVisibility(View.INVISIBLE); 
		   
		   /*
		    * RECUPERATION DU BOUTON QUI SERA AFFICHER LORS DE LANCEMENT DE L'APPAREIL PHOTO ET QUI PERMET DE PRENDRE UNE PHOTO  
		    */
		   //Button boutonPrendrePhoto = (Button)v.findViewById(R.id.data_camera_prendre_photo_bouton);
		   ImageButton imageButton = (ImageButton)v.findViewById(R.id.data_camera_prendre_photo_bouton_image); 
		   imageButton.setOnClickListener(new View.OnClickListener() {
			   
		   		public void onClick(View v) {
   			        //LA METHODE PAS ENCORE IMPLEMENTEE 
   					//getActivity().finish();
   			         if(camera != null) {
   			        	 camera.takePicture(shutterCallback, null, pictureCallback); 
   			         }
		   }
	    });
		   /*
		    * GERER LE CLICK SUR LE BOUTON PRENDRE UNE PHOTO
		    */
//		   boutonPrendrePhoto.setOnClickListener(new View.OnClickListener() {
//					   
//			   		public void onClick(View v) {
//			   			        //LA METHODE PAS ENCORE IMPLEMENTEE 
//			   					//getActivity().finish();
//			   			         if(camera != null) {
//			   			        	 camera.takePicture(shutterCallback, null, pictureCallback); 
//			   			         }
//					   }
//				    });
	       
		   /*
		    * RECUPERATION DE LA SURFACE DE PREVISUALISATION DE LA PHOTO
		    */
		   maSurfaceDeVue = (SurfaceView)v.findViewById(R.id.data_camera_surfaceView);	
		   /*
		    * L'OBJET SurfaceVeiw NOUS NE PERMET PAS DE LE MANIPULER DIRECTEMENT   DONC IL FAUT RECUPERER L'OBJET  Holder QUI LE PERMET
		    * 
		    */
		    SurfaceHolder holder = maSurfaceDeVue.getHolder();
		    
	        /*
	         *  deprecated, but required for pre-3.0 devices
	         *  setType ??????????????????????????????????????
	         */
	        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	        /*
	         * ON RAJOUTE UN CALLBACK A LA SurfaceView  OU ENCORE DIRECTEMENT AU HOLDER CELA PERMET DE ECOUTER CE QUI SE PASSE SUR LA SURFACE 
	         * AVOIR DES RAPPEL 
	         * L'INTERFACE =  SurfaceHolder.CallBack   CETTE INTERFACE PEUT ETRE DIRECTEMENT IMPLEMENT AU NIVVEAU DU FRAGEMENT 
	         * 
	         */
	        holder.addCallback(new SurfaceHolder.Callback() {

	        									  /*
	        									   * CETTE METHODE EST APPELEE IMMEDIALTEMENT APRES LA CREATION DE LA SurfaceView
	        									   */
									        	  public void surfaceCreated(SurfaceHolder holder) {
										        		  /*
										        		   * LAISSE LA CAMERA UTILISER CETTE SURFACE POUR VISUALISER LES CHAMP FILME
										        		   */
										                  try {
										                      if (camera != null) {
										                    	  camera.setPreviewDisplay(holder);
										                      }
										                  } catch (IOException exception) {
										                      	  Log.e(TAG, " --> ERREUR DE PAREMETRAGE POUR LA PREVISUALISATION DE LA CAMERA", exception);
										                  }
									              }
											   
												   /*
												    * METHODE APPELLEE IMMIDIALEMENT AVANT LA DESTRUCTION DE LA SURFACE
												    */
										            public void surfaceDestroyed(SurfaceHolder holder) {
											                /*
											                 * ARRETER LA PREVISUALISATION
											                 */
											                if (camera != null) {
											                	camera.stopPreview();
											                }
										            }
								
								                /*
								                 * APPELEE APRES UN CHANGEMENT DE TAILLE OU DE FORMAT DE LA SURFACE 
								                 */
											    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
									            	if (camera == null) return;
									            	
									                // the surface has changed size; update the camera preview size
									                Camera.Parameters parameters = camera.getParameters();
									                Size s = getBestSupportedSize(parameters.getSupportedPreviewSizes(), w, h);
									                parameters.setPreviewSize(s.width, s.height);
									                s= getBestSupportedSize(parameters.getSupportedPictureSizes(), w, h); 
									                parameters.setPictureSize(s.width, s.height); 
									                camera.setParameters(parameters);
									                try {
									                	camera.startPreview();
									                } catch (Exception e) {
									                    Log.e(TAG, " --> NE PEUT PAS LANCER LA PREVISUALISATION", e);
									                    /*
									                     * CETTE INSTRUCTION PERMET DE LIBERER LES RESSOURCES OCCUPEES PAR LA CAMERA  
									                     */
									                    camera.release();
									                    camera = null;
									                }
									            }
			    
			   });
		   
	       return v; 
	}
	
	/*
	 * POURQUOI OUVRIR LA CEMARA DANS LA METHODE RESUME ???????????????
	 */
    @TargetApi(9)
    @Override
    public void onResume() {
        super.onResume();
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {    
            camera = Camera.open(0);
        } else {
        	camera = Camera.open();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (camera != null) {
        	camera.release();
        	camera = null;
        }
    }

    /* 
     * RENVOI L'ESPACE DISPONIBLE EN LARGEUR POUR LA CAMERA
     * a simple algorithm to get the largest size available. For a more 
     * robust version, see CameraPreview.java in the ApiDemos 
     * sample app from Android. 
    */
    private Size getBestSupportedSize(List<Size> sizes, int width, int height) {
        Size bestSize = sizes.get(0);
        int largestArea = bestSize.width * bestSize.height;
        for (Size s : sizes) {
            int area = s.width * s.height;
            if (area > largestArea) {
                bestSize = s;
                largestArea = area;
            }
        }
        return bestSize;
    }
	
}
