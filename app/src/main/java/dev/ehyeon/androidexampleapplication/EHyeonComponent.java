package dev.ehyeon.androidexampleapplication;

import dagger.Component;

@Component(modules = {UserModule.class})
public interface EHyeonComponent {

    UserDao getUserDao();
}
