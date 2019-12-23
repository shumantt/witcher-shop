public class Manager {

    enum Page {

        Reports(1),
        Employee(2);

        int id;

        Page(int id) {
            this.id = id;
        }
    }

    enum Report {

        General(1),
        Costs(2),
        Load(3),
        KPI(4);

        int id;

        Report(int id) {
            this.id = id;
        }
    }


    static void createNewUser(String name, WorkPosition position) { // Test 4
        navigateToManagerPage(Page.Employee);

        Utils.sendToTextField("//*[@id=\"name\"]", name);

        Utils.pickFromListBy("/html/body/div/div/div/main/div/div/div/div[2]/div[1]/div/div[2]/div/div", position.id);

        Utils.elementBy("/html/body/div/div/div/main/div/div/div/div[2]/button/div").click();
    }

    static void managerReport(Report report) { // Test 5, 6, 7
        navigateToManagerPage(Page.Reports);
        Utils.elementBy("/html/body/div/div/div/main/div/div/div/div/div[1]/button[" + report.id + "]").click();
    }

    static void navigateToManagerPage(Page page) {
        Auth.manager();
        Utils.elementBy( "/html/body/div/div/div/div/div/div[2]/div/div/ul/li[" + page.id +"]").click();
    }
}
