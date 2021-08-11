package com.redhat.shipping;

public class RegionNotFoundException extends Exception {

    private static final long serialVersionUID = -5692072907291015767L;

    public RegionNotFoundException(String name) {
        super(String.format("Region %s is not available", name));
    }

}