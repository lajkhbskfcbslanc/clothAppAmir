package com.example.sekhatapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class HoodiFrag extends Fragment implements colorIDs {

    private RecyclerView recyclerView,newitemRec;
    private RecyclerView.Adapter recyclerViewAdapter,newitemRecAdapter;
    List<MainPropRecycle> mainPropRecycleList;
    List<newItemProp> newItemPropList;
    LinearLayout linearUp,linearDown;
    Animation animationUp,animationDown;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoodi, container, false);

        recyclerView = view.findViewById(R.id.hoodiHolderRec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        new CardSnapHelper().attachToRecyclerView(recyclerView);

        newitemRec = view.findViewById(R.id.newItemHoodiHolderRec);
        newitemRec.setHasFixedSize(true);
        newitemRec.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        mainPropRecycleList = new ArrayList<>();
        newItemPropList = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shoe_sekhat);
        Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shoe_test);
        for (int i = 0; i <10 ; i++) {
            tshirtPropSetter(new MainPropRecycle("Tshirt","10000", getContext().getResources().getDrawable(colorBack[i]),bitmap));

        }
        for (int i = 0; i <9 ; i++) {
            newItemSetter(new newItemProp("Tshirt","10000",newBitmap));

        }

        linearUp=view.findViewById(R.id.hoodiDown);
        linearDown=view.findViewById(R.id.hoodiup);

        animationUp= AnimationUtils.loadAnimation(getContext(), R.anim.productpage_anim_up);
        animationDown=AnimationUtils.loadAnimation(getContext(),R.anim.productpage_anim_down);



        recyclerViewAdapter=new AdapterRecycleMain(mainPropRecycleList);
        recyclerView.setAdapter(recyclerViewAdapter);
        newitemRecAdapter=new NewItemAdapter(newItemPropList);
        newitemRec.setAdapter(newitemRecAdapter);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        linearUp.startAnimation(animationUp);
        linearDown.startAnimation(animationDown);
        Log.i(TAG, "onResume: Hoodi");
    }

    @Override
    public void onStart() {
        super.onStart();
        linearUp.startAnimation(animationUp);
        linearDown.startAnimation(animationDown);
        Log.i(TAG, "onResume: Hoodi");
    }

    private void tshirtPropSetter(MainPropRecycle mainProp){
        MainPropRecycle mainPropRecycle=mainProp;
        mainPropRecycleList.add(mainPropRecycle);
    }
    private void newItemSetter(newItemProp newItemProp1){
        newItemProp newItemProp2=newItemProp1;
        newItemPropList.add(newItemProp2);
    }
}