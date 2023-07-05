package dev.ehyeon.androidexampleapplication.presentation;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dev.ehyeon.androidexampleapplication.R;

public enum MainFragmentType {

    DAGGER(R.id.item_dagger, "dagger"),
    RETROFIT2(R.id.item_retrofit2, "retrofit2");

    private static final Map<Integer, String> MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(MainFragmentType::getId, MainFragmentType::getTag)));

    private final int id;
    private final String tag;

    MainFragmentType(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public static MainFragmentType of(int id) {
        return MainFragmentType.valueOf(MAP.get(id).toUpperCase());
    }
}
