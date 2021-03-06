package com.gtbr.hexapi.repository;

import com.gtbr.hexapi.entity.Version;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VersionRepository extends CrudRepository<Version, String> {

    @Query("select v from Version v where v.launchedAt is not null and v.endedAt is null")
    Version findActualVersion();
}
