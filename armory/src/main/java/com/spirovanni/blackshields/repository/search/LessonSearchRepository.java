package com.spirovanni.blackshields.repository.search;

import com.spirovanni.blackshields.domain.Lesson;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Lesson entity.
 */
public interface LessonSearchRepository extends ElasticsearchRepository<Lesson, Long> {
}
