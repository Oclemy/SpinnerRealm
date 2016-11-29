package com.tutorials.hp.spinnerrealm.m_Realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Oclemy on 6/14/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class RealmHelper {

    Realm realm;


    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //WRITE
    public void save(final Spacecraft spacecraft)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Spacecraft s=realm.copyToRealm(spacecraft);

            }
        });
    }


    //READ/RETRIEVE
    public ArrayList<String> retrieve()
    {
        ArrayList<String> spacecraftNames=new ArrayList<>();
        RealmResults<Spacecraft> spacecrafts=realm.where(Spacecraft.class).findAll();

        for(Spacecraft s: spacecrafts)
        {
            spacecraftNames.add(s.getName());
        }

        return spacecraftNames;
    }
}













