/**package test;

import modele.IDException;
import modele.LectureScenario;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureScenarioTest {

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    @Test
    void lectureScenarioFichierExistant() {
        System.out.println("lectureScenario - fichier existant");

        try {
            List[] resultat = LectureScenario.lectureScenario("scenario_0.txt");

            assertNotNull(resultat, "Le résultat ne doit pas être nul");
            assertEquals(2, resultat.length, "Le tableau doit contenir deux listes (vendeurs et acheteurs)");
            assertNotNull(resultat[0], "La première liste doit contenir les vendeurs");
            assertNotNull(resultat[1], "La deuxième liste doit contenir les acheteurs");
            assertEquals(resultat[0].size(), resultat[1].size(), "Les deux listes doivent avoir la même taille");

        } catch (IDException | FileNotFoundException e) {
            fail("Exception levée alors que le fichier existe : " + e.getMessage());
        }
    }

    @Test
    void lectureScenarioFichierInexistant() {
        System.out.println("lectureScenario - fichier inexistant");

        assertThrows(FileNotFoundException.class, () -> {
            LectureScenario.lectureScenario("fichier_inexistant.txt");
        }, "Une FileNotFoundException était attendue pour un fichier inexistant");
    }
}
*/