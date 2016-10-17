package com.example.neeraj.pg_guide;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

public class sessionmanager
{
  SharedPreferences pref;
  Editor editor;
  Context context1;
  public sessionmanager(Context context) //Context is used to store the refrence of current value
  {
	this.context1=context;
	  pref=context1.getSharedPreferences("oopsinf", 0);
	  editor=pref.edit();
	  
  }
   public void createloginsession(prop_detail d)
   {
	   try
	   {
	  editor.putString("name",d.name);
	   editor.putString("city",d.city);

		 editor.putString("sector",d.sector);
		   editor.putString("mobileno",d.phone);

		   Log.d("sd-------",d.name);
	   
	   editor.commit();
	   }
	   catch(Exception e)
	   {
		   
	   }
	   
   }
   public HashMap<String , String> getuserdetail()
   {
	   HashMap<String , String> user =new HashMap<String, String>();
	 //  user.put("username", pref.getString("username", "no user name in session"));
	   user.put("name", pref.getString("name", "no name in session"));
	  // user.put("password", pref.getString("password", "no password in session "));
	 //  user.put("email", pref.getString("password", "no email idin session "));
	//   user.put("profession", pref.getString("profession", "no profession in session "));
	  // user.put("address", pref.getString("address", "no address in session "));
	   user.put("sector", pref.getString("sector", "no sector in session "));
	   user.put("city", pref.getString("city", "no pass in session "));
	  user.put("mobileno", pref.getString("mobileno", "no mobile in session "));

	return user;
   }
   public void  logoutuser()
   {
	editor.clear();
	editor.commit();
   }
}
