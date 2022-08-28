package javaNormal;

interface Info {
    int getLevel();
}

class EmployeeInfo implements Info{
    public int rank;
    EmployeeInfo(int rank) {
        this.rank = rank;
    }

    @Override
    public int getLevel() {
        return this.rank;
    }
}

class Person<T extends Info>{
    public T info;
    Person(T info){
        this.info = info;
    }
}


