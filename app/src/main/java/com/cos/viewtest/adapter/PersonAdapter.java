package com.cos.viewtest.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.viewtest.MainActivity;
import com.cos.viewtest.R;
import com.cos.viewtest.model.Person;

import java.util.ArrayList;
import java.util.List;

// 2. 어댑터 만들기(어댑터 상속)
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private PersonAdapter personAdapter = this;
    private static final String TAG = "MyTag";
    private MainActivity mContext;

    public PersonAdapter(MainActivity mContext){
        this.mContext = mContext;
    }

    // 3. 컬렉션
    private List<Person> persons = new ArrayList<>();

    // 4. 컬렉션 데이터 세팅
    public void addItems(List<Person> persons){

        this.persons = persons;
        notifyDataSetChanged(); // 데이터 변경하는곳에는 무조건 걸어줘야됨
    }

    public List<Person> getItems(){
        return persons;
    }

    public void removeItem(int index){
        persons.remove(index);
        notifyDataSetChanged();
    }

    // 아이템 1개 추가
    public void addItem(Person person){
        this.persons.add(person);
        notifyDataSetChanged();
        mContext.mRvScroll();
    }

    // ViewHolder 객체 만드는 메서드, 그림만 그림
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.person_item, parent, false);


        return new MyViewHolder(view);
    }

    // ViewHolder 데이터 갈아끼우는 메서드, 그려진 그림에 데이터를 끼워넣음
    @Override
    public void onBindViewHolder(PersonAdapter.MyViewHolder holder, int position) {
        Person person = persons.get(position);
        holder.setItem(person);
    }

    // adapter가 알아서 호출해서 Item의 크기를 확인함
    @Override
    public int getItemCount() {
        return persons.size();
    }

    // 1. 뷰홀더 만들기(뷰홀더 상속)
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvTel;

        // 앱 구동시에 발동
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel = itemView.findViewById(R.id.tvTel);

            initListener();
        }

        private void initListener(){
            itemView.setOnClickListener(v ->{
                Log.d(TAG, "onCreateViewHolder: "+ getAdapterPosition());
                int index = getAdapterPosition();
                Log.d(TAG, "initListener: "+personAdapter.getItems().get(index).getName());
                personAdapter.removeItem(index);
                // view를 통해서 값을 찾기
//                TextView t = v.findViewById(R.id.tvName);
//                Log.d(TAG, "initListener: "+t.getText());
            });
        }

        // 앱 구동시 + 스크롤할 때 발동동
        public void setItem(Person person){
            tvName.setText(person.getName());
            tvTel.setText(person.getTel());
        }
    }
}
