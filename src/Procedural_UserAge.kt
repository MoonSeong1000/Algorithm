//var users = [
//    {
//        name: 'a', age: 21, family: [
//        { name: 'a1', age: 53 }, { name: 'a2', age: 47 },
//        { name: 'a3', age: 16 }, { name: 'a4', age: 15 }
//        ]
//    },
//    {
//        name: 'b', age: 24, family: [
//        { name: 'b1', age: 58 }, { name: 'b2', age: 51 },
//        { name: 'b3', age: 19 }, { name: 'b4', age: 22 }
//        ]
//    },
//    {
//        name: 'c', age: 31, family: [
//        { name: 'c1', age: 64 }, { name: 'c2', age: 62 }
//        ]
//    },
//    {
//        name: 'd', age: 20, family: [
//        { name: 'd1', age: 42 }, { name: 'd2', age: 42 },
//        { name: 'd3', age: 11 }, { name: 'd4', age: 7 }
//        ]
//    }
//];

data class User(var name: String, var age: Int, var family: List<User> = listOf());

val userList: List<User> = listOf(
        User("a", 21,
                listOf<User>(User("a1", 53), User("a2", 47),
                        User("a3", 16), User("a4", 15)
                )
        ),
        User("b", 24,
                listOf<User>(User("b1", 58), User("b2", 51),
                        User("b3", 19), User("b4", 22)
                )
        ),
        User("c", 31,
                listOf<User>(User("c1", 64), User("c2", 62))
        ),
        User("d", 20,
                listOf<User>(User("d1", 42), User("d2", 42),
                        User("d1", 11), User("d2", 7)
                )
        )
);

fun main() {
    var ageList : ArrayList<Int> = ArrayList<Int>()

    for(i in userList) {
        for(j in i.family) {
            if(j.age>=20) {
                ageList.add(j.age)
            }
        }
    }

    var answer = 0;
    ageList.sortDescending()
    repeat(4) {
        answer += ageList[it]
    }
    println(ageList)
}