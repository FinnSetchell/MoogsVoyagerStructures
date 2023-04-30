package com.finndog.mvs.modinit.registry;

import java.util.Collection;
import java.util.function.Supplier;

public class resourcefulRegistryChild<T> implements resourcefulRegistry<T> {

    private final resourcefulRegistry<T> parent;
    private final RegistryEntries<T> entries = new RegistryEntries<>();

    public resourcefulRegistryChild(resourcefulRegistry<T> parent) {
        this.parent = parent;
    }

    @Override
    public <I extends T> RegistryEntry<I> register(String id, Supplier<I> supplier) {
        return this.entries.add(parent.register(id, supplier));
    }

    @Override
    public Collection<RegistryEntry<T>> getEntries() {
        return entries.getEntries();
    }

    @Override
    public void init() {
        //NO-OP
    }
}