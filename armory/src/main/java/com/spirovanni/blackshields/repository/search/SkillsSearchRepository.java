package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.Skills;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Skills entity.
 */
public interface SkillsSearchRepository extends ElasticsearchRepository<Skills, Long> {
}
