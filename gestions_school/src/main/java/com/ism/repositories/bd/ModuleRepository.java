package com.ism.repositories.bd;

import java.util.List;

import com.ism.entities.Modules;
import com.ism.repositories.core.Itables;

public interface ModuleRepository extends Itables<Modules> {
    List<Module> findModulesByClasse(int classeId);
}
