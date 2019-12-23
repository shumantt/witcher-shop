enum EmployeePages {

    Costs(1),
    Orders(2);

    int id;

    EmployeePages(int id) {
        this.id = id;
    }
}

enum AlchemistPages {

    Receipts(1),
    Grass(1),
    Animals(1),
    Runes(2);

    int id;

    AlchemistPages(int id) {
        this.id = id;
    }
}

enum WorkPosition {

    Employee(1),
    Manager(2);

    int id;

    WorkPosition(int id) {
        this.id = id;
    }
}

enum ResourceManagement {
    Spent(1),
    Earned(2);

    int id;

    ResourceManagement(int id) {
        this.id = id;
    }
}