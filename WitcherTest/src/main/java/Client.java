public class Client {

    enum Page {
        MakeOrder(1),
        CheckOrder(2);

        int id;

        Page(int id) {
            this.id = id;
        }
    }

    static void makeOrder() { // Test 15
        Auth.client();
        openPage(Page.MakeOrder);

        Utils.pickFromListBy("/html/body/div/div/div/main/div/div/div/div[2]/div/div[2]/div/div", 1);
        Utils.sendToTextField(1, "TestName");
        Utils.sendToTextField(2, "2");
        Utils.sendToTextField(3, "222");
        Utils.elementBy("/html/body/div[1]/div/div/main/div/div/div/div[6]/button[1]").click();
    }

    static void checkOrder() { // Test 16
        Auth.client();
        openPage(Page.CheckOrder);
    }

    static void openPage(Page page) {
        Utils.elementBy("/html/body/div/div/div/div/div/div[2]/div/ul/li[" + page.id + "]").click();
    }
}
