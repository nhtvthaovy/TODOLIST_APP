package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {

    private MainActivity context; // Khai báo biến context để lấy ngữ cảnh của MainActivity
    private int layout; // Khai báo biến layout để lưu layout của từng item
    private List<CongViec> congViecList; // Khai báo danh sách công việc

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) {
        this.context = context; // Gán giá trị context
        this.layout = layout; // Gán giá trị layout
        this.congViecList = congViecList; // Gán danh sách công việc
    }

    @Override
    public int getCount() {
        return congViecList.size(); // Trả về số lượng công việc
    }

    @Override
    public Object getItem(int position) {
        return congViecList.get(position); // Trả về công việc tại vị trí position
    }

    @Override
    public long getItemId(int position) {
        return congViecList.get(position).getIdCV(); // Trả về ID của công việc tại vị trí position
    }

    private class ViewHolder {
        TextView txtTen; // Khai báo TextView để hiển thị tên công việc
        ImageView imgDelete, imgEdit; // Khai báo ImageView để hiển thị nút xóa và sửa
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder; // Khai báo ViewHolder để chứa các thành phần của một item
        if (convertView == null) { // Nếu convertView chưa được tạo
            holder = new ViewHolder(); // Tạo mới ViewHolder
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // Lấy đối tượng LayoutInflater để chuyển layout XML thành đối tượng View
            convertView = inflater.inflate(layout, null); // Gán layout cho convertView
            holder.txtTen = convertView.findViewById(R.id.textviewTen); // Ánh xạ TextView
            holder.imgEdit = convertView.findViewById(R.id.imgviewEdit); // Ánh xạ ImageView cho nút sửa
            holder.imgDelete = convertView.findViewById(R.id.imgviewDelete); // Ánh xạ ImageView cho nút xóa
            convertView.setTag(holder); // Lưu trữ ViewHolder trong convertView
        } else {
            holder = (ViewHolder) convertView.getTag(); // Lấy ViewHolder ra nếu đã tồn tại
        }

        CongViec congViec = congViecList.get(position); // Lấy công việc tại vị trí position
        holder.txtTen.setText(congViec.getTenCV()); // Đặt tên công việc vào TextView

        // Bắt sự kiện khi nhấn vào nút sửa
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức DialogSuaCongViec trong MainActivity để hiển thị dialog sửa công việc
                context.DialogSuaCongViec(congViec.getTenCV(), congViec.getIdCV());
            }
        });

        // Bắt sự kiện khi nhấn vào nút xóa
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức Xoa trong MainActivity để xóa công việc
                context.Xoa(congViec.getTenCV(), congViec.getIdCV());
            }
        });

        return convertView; // Trả về convertView
    }
}
