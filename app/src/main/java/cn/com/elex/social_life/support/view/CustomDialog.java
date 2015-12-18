package cn.com.elex.social_life.support.view;

import android.content.Context;
import android.view.View;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

/**
 * Created by zhangweibo on 2015/11/9.
 */
public class CustomDialog {

   public    NiftyDialogBuilder dialogBuilder;


    public CustomDialog(Context context,String title,String content,View.OnClickListener certain,View.OnClickListener cancel) {

        dialogBuilder=NiftyDialogBuilder.getInstance(context);
        defaultConfigure(title,content,certain,cancel);

    }


    public void show(){
        dialogBuilder.show();
    }


    public void dismiss(){
        dialogBuilder.dismiss();
    }
    /*   ase R.id.fadein:effect=Effectstype.Fadein;break;
        case R.id.slideright:effect=Effectstype.Slideright;break;
        case R.id.slideleft:effect=Effectstype.Slideleft;break;
        case R.id.slidetop:effect=Effectstype.Slidetop;break;
        case R.id.slideBottom:effect=Effectstype.SlideBottom;break;
        case R.id.newspager:effect=Effectstype.Newspager;break;
        case R.id.fall:effect=Effectstype.Fall;break;
        case R.id.sidefall:effect=Effectstype.Sidefill;break;
        case R.id.fliph:effect=Effectstype.Fliph;break;
        case R.id.flipv:effect=Effectstype.Flipv;break;
        case R.id.rotatebottom:effect=Effectstype.RotateBottom;break;
        case R.id.rotateleft:effect=Effectstype.RotateLeft;break;
        case R.id.slit:effect=Effectstype.Slit;break;
        case R.id.shake:effect=Effectstype.Shake;break;*/

    public void defaultConfigure(String title,String content,View.OnClickListener certain,View.OnClickListener cancel){

        dialogBuilder
                .withTitle(title)                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage(content)                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
              //  .withIcon(getResources().getDrawable(R.drawable.icon))
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(Effectstype.Fadein)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
           //     .setCustomView(R.layout.custom_view,v.getContext())         //.setCustomView(View or ResId,context)
                .setButton1Click(cancel)
                .setButton2Click(certain)
                .show();
    }




}
