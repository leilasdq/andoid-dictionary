package com.example.homework10.Controller;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.homework10.Model.Dictionary;
import com.example.homework10.R;
import com.example.homework10.Reposotory.WoldsRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private Dictionary mDictionary;
    private SearchView mSearchView;
    private List<Dictionary> mDictionaries;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    DictionaryHelper mDictionaryHelper;

    private TextView englishWord, persianWord;
    private DictionaryAdapter adapter;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initUI(view);
        setAdapter();
        onClicks();

        return view;
    }

    private void setAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DictionaryAdapter(mDictionaries);
        mRecyclerView.setAdapter(adapter);
    }

    private void onClicks() {
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchView.setIconified(false);
            }
        });
    }

    private void initUI(View view) {
        mSearchView = view.findViewById(R.id.search_view);
        mToolbar = view.findViewById(R.id.toolbar);
        mRecyclerView = view.findViewById(R.id.recycler);

        mDictionaries = WoldsRepository.getInstance(getContext()).getAllWorlds();
    }

    private class DictionaryHolder extends RecyclerView.ViewHolder {
        public DictionaryHolder(@NonNull View itemView) {
            super(itemView);

            englishWord = itemView.findViewById(R.id.english_word);
            persianWord = itemView.findViewById(R.id.persian_word);
        }

        public  void bind (Dictionary dictionary){
            mDictionary = dictionary;
            englishWord.setText(mDictionary.getEngWorld());
            persianWord.setText(mDictionary.getPerWorld());
        }
    }

    private class DictionaryAdapter extends RecyclerView.Adapter<DictionaryHolder> {
        List<Dictionary> mList;
        private List<Dictionary> fullList;

        public DictionaryAdapter(List<Dictionary> list) {
            mList = list;
            fullList = new ArrayList<>(list);
        }

        @NonNull
        @Override
        public DictionaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_items, parent, false);
            return new DictionaryHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DictionaryHolder holder, int position) {
            holder.bind(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

}
