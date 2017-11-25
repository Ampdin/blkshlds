package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.Discipline;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Discipline entity.
 */
public interface DisciplineSearchRepository extends ElasticsearchRepository<Discipline, Long> {
}
