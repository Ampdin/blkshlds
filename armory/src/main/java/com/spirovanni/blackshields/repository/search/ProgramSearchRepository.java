package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.Program;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Program entity.
 */
public interface ProgramSearchRepository extends ElasticsearchRepository<Program, Long> {
}
