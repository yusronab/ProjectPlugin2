package com.example.weekfinal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.weekfinal.*
import com.example.weekfinal.databinding.ActivityFragmentBinding
import com.example.weekfinal.fragment.*
import com.example.weekfinal.response.Communicator
import com.example.weekfinal.webservice.Constant

class FragmentActivity : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityFragmentBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.Open, R.string.Close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.drawerNav.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.drawerLogout -> doLogout()
            }
            true
        }

        setFragment(HomeFragment())
        bottomNav()
    }

    private fun doLogout(){
        Constant.clearToken(this)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun bottomNav(){
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.bottomHome -> setFragment(HomeFragment())
                R.id.bottomAdd -> setFragment(NewDataFragment())
                R.id.bottomAccount -> setFragment(AccountFragment())
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun passData(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, detailFragment)
        transaction.commit()
    }

    override fun passId(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val editFragment = EditFragment()
        editFragment.arguments = bundle

        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, editFragment)
        transaction.commit()
    }

    override fun passNama(nama: String) {
        val bundle = Bundle()
        bundle.putString("nama", nama)

        val editFragment = EditFragment()
        editFragment.arguments = bundle

        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, editFragment)
        transaction.commit()
    }

    override fun passKode(kode: Int) {
        val bundle = Bundle()
        bundle.putInt("kode", kode)

        val editFragment = EditFragment()
        editFragment.arguments = bundle

        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, editFragment)
        transaction.commit()
    }

    override fun createData(name: String) {
        val bundle = Bundle()
        bundle.putString("name", name)

        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle

        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, homeFragment)
        transaction.commit()
    }
}