package com.codex.tourmate.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codex.tourmate.R;
import com.codex.tourmate.event_class.Expense;

import java.util.List;

public class ExpenseViewAdapter extends RecyclerView.Adapter<ExpenseViewAdapter.ExpenseViewHolder>{


    private List<Expense> expenseList;
    private Context context;

    public ExpenseViewAdapter(List<Expense> expenseList, Context context) {
        this.expenseList = expenseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.expense_view_model,parent,false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{

        TextView textView,amountView,dateView;

        public ExpenseViewHolder(View itemView) {
            super(itemView);
            amountView = itemView.findViewById(R.id.expense_amount_view);
            textView = itemView.findViewById(R.id.expense_details_view);
            dateView = itemView.findViewById(R.id.expense_date_view);

        }
    }
}
