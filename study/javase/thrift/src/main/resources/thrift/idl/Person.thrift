namespace java com.org.asean.javase.server
struct Person{
1: i32 id,
2: string name,
3: i32 age
}

service PersonService{

    Person getPerson();
}