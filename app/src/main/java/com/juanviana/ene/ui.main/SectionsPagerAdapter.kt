package com.juanviana.ene.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.juanviana.ene.MicuentaFragment
import com.juanviana.ene.PedidosFragment
import com.juanviana.ene.PerfilFragment
import com.juanviana.ene.R


private val TAB_TITLES = arrayOf(
    R.string.mi_cuenta,
    R.string.mis_pedidos,

)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: PerfilFragment, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
       when (position){
           0 -> return MicuentaFragment()
           else -> return  PedidosFragment()
       }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}