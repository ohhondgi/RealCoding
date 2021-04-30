import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ChampionTest {
    private List<Champion> championList = new ArrayList<Champion>();

    @Before // 각 테스트 메소드 실행 전에 실행됌.
    public void setUp() {

        //5개의 챔피언 객체를 만듭니다.
        Champion topChamp = new Champion("다리우스", "탑");
        Champion jungleChamp = new Champion("리신", "정글");
        Champion midChamp = new Champion("르블랑", "미드");
        Champion adcChamp = new Champion("베인", "바텀");
        Champion supportChamp = new Champion("레오나", "바텀");

        //앞서 만든 List 에 각 챔피언을 추가합니다.
        championList.add(topChamp);
        championList.add(jungleChamp);
        championList.add(midChamp);
        championList.add(adcChamp);
        championList.add(supportChamp);
    }

    //List<String>을 생성하고 값이 비어 있는지를 테스트 empty()
    @Test
    public void givenCollectionWhenEmptyCorrect() {
        List<String> emptyList = new ArrayList<>();
        assertTrue(emptyList.isEmpty());
        assertThat(emptyList,empty());
    }

    //notNullValue 활용한 테스트
    @Test
    public void notNullCheck() {
        String lck = "LCK";
        assertThat(lck,notNullValue());
    }

    //nullValue 활용한 테스트
    @Test
    public void givenStringWhenNullIsCorrect() {
        String lck = null;
        assertThat(lck, nullValue());
    }

    //문자열 관련 테스트 anyOf, containsString, endsWith
    @Test
    public void testForRelatedString() {
        String sampleString1 = "Player Focus";
        String sampleString2 = "Player point";
        String startString = "Player";
        String endString = "point";

        // startsWith은 시작단어가 일치해야함
        assertThat(sampleString1, startsWith("Player"));
        // endsWith은 끝 단어가 일치
        assertThat(sampleString2, endsWith("point"));
        // containsString은 중간에 단어가 있으면 됨
        assertThat(startString,containsString("Pla"));
        // anyOf는 조건을 여러 개의 값을 비교할 수 있다. 여러 개의 조건 중 하나만 true여도 됨
        assertThat(endString,anyOf(notNullValue(), containsString("Focus")));
        // allOf는 조건이 여러가지일 경우, 모든 조건이 참이어야 함
        assertThat(sampleString2, allOf(startsWith("Player"), endsWith("point")));
    }

    //부동소수점 범위 closeTo 테스트
    @Test
    public void testForFloatingPoint() {
        //closeTo 는 근사치를 보는 method
        assertThat(3.14, closeTo(3, 0.2));
    }

    //anything 테스트
    @Test
    public void shouldNotErrorGetReference() {
        System.out.println("champion info :: " + championList.get(0));
        // 어떤 요구조건에도 true를 반환하여 logic test에 사용
        assertThat(championList.get(0), anything());
    }

    //객체 크기 검증 테스트 hasSize
    @Test
    public void shouldChampionCountFive() {
        assertThat(championList.size(), is(5));
        assertThat(championList,hasSize(5));
    }

    //타릭 챔피언 객체를 만들고 이름과 포지션을 검증하세요.
    @Test
    public void shouldSupportChampionIsTaric() {
        Champion supportChamp = new Champion("타릭", "바텀");
        assertThat(supportChamp.getName(), is("타릭"));
        assertThat("타릭",equalTo(supportChamp.getName()));
        assertThat("바텀", equalTo(supportChamp.getPosition()));
    }

    //hasProperty 활용하여 속성이 포함되어 있는지 테스트
    @Test
    public void shouldHasPropertyPosition() {
        assertThat(championList.get(0), hasProperty("position"));
        assertThat(championList.get(1), hasProperty("name"));
        // property의 값이 맞는지 검증도 가능
        assertThat(championList.get(2), hasProperty("position",equalTo("미드")));
        System.out.println("champion :: " + championList.get(2));
    }

    //hasToString 활용 테스트
    @Test
    public void shouldHaveSomeChampName() {
        List<String> champListNames = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가느", "블라디미르");
        // hasToString은 문자열 전체 비교. is나 equal로도 대체가능
        assertThat(champListNames.get(2),hasToString("렉사이"));
        assertThat(champListNames.get(0),containsString("루"));
    }

    //property와 value가 같은지 테스트
    @Test
    public void shouldHaveSamePropertyAndValue() {
        List<String> championNames1 = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가나", "블라디미르");
        List<String> championNames2 = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가나", "블라디미르");
        List<String> champArray = championNames1;
        // samePropertyValuesAs는 두 객체의 property가 같아야함
        assertThat(championNames1,samePropertyValuesAs(championNames2));
        assertThat(championNames1, samePropertyValuesAs(champArray));
    }

    //탑 챔피언은 다리우스여야 한다라는 조건으로 테스트 코드 작성, stream 활용예
    @Test
    public void shouldTopChampionIsDarius() {
        // optional은 객체를 명확하게 정의하기 힘들 경우 (객체)
        Optional<Champion> filteredChampion = championList.stream()
                .filter(c -> c.getPosition().equals("탑"))
                .findFirst();
        assertThat(filteredChampion.get().getName(), is("다리우스"));
        assertTrue(filteredChampion.get().getPosition().equals("탑"));
        System.out.println("champ:: " + filteredChampion);
    }

}