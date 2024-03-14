package persistence.sql.ddl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.entity.testfixture.notcolumn.Person;
import persistence.sql.ddl.clause.TableClause;
import persistence.sql.ddl.querybuilder.CreateQueryBuilder;

import java.util.List;

class CreateQueryBuilderTest {
    @Test
    @DisplayName("[요구사항 1] @Column 애노테이션이 없는 Person 엔티티를 이용하여 create 쿼리 만든다.")
    void 요구사항1_test() {
        //given
        String expectedQuery = "CREATE TABLE IF NOT EXISTS Person " +
                "(id Long AUTO_INCREMENT PRIMARY KEY,name VARCHAR(30) NULL,age INT NULL)";
        // when
        String actualQuery = new CreateQueryBuilder(persistence.entity.testfixture.basic.Person.class).getQuery();

        // then
        Assertions.assertThat(actualQuery).isEqualTo(expectedQuery);
    }

    @Test
    @DisplayName("[요구사항 3.2] @Transient 애노테이션이 붙은 필드는 제하고 컬럼 만든다.")
    void 요구사항3_2_test() {
        //given
        List<String> expectedColumnQueries = List.of("nick_name VARCHAR(30) NULL",
                "old INT NULL",
                "email VARCHAR(30) NOT NULL");

        // when
        List<String> actualColumnQueries = new TableClause(Person.class).columnQueries();

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedColumnQueries.containsAll(actualColumnQueries)).isTrue();
        softAssertions.assertThat(actualColumnQueries).hasSize(3);
    }
}
