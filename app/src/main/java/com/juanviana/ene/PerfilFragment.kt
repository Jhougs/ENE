package com.juanviana.ene

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.juanviana.ene.ui.main.SectionsPagerAdapter


class PerfilFragment : Fragment() {
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
           val view: View = inflater.inflate(R.layout.fragment_perfil, container, false)
           val fragmentAdapter = SectionsPagerAdapter(this,childFragmentManager)
           val viewPager: ViewPager = view.findViewById(R.id.viewPager)
           viewPager.adapter = fragmentAdapter
           val tabs: TabLayout = view.findViewById(R.id.data_sheet_tabs)
           tabs.setupWithViewPager(viewPager)

           return view

           

       }




}