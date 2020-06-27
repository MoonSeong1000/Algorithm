import java.util.*

const val MOVE_CNT = 8
val dx = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
val dy = arrayOf(0, 1, 1, 1, 0, -1, -1, -1)
var rowSize = 0
var colSize = 0

fun log(board:Array<CharArray>) {
    for(i in board.indices) {
        for(element in board[i]) {
            print("$element ")
        }
        println()
    }
}


fun isBoundary(verifyX:Int,verifyY:Int):Boolean{
    return (verifyX > 0 || verifyX >= rowSize || verifyY < 0 || verifyY >= colSize)
}

fun getMineCount(board: Array<CharArray>, x: Int, y: Int): Int {
    var mineCnt = 0
    repeat(MOVE_CNT) {
        val nextX = x + dx[it]
        val nextY = y + dy[it]

        if (isBoundary(nextX,nextY) && board[nextX][nextY]=='M')
            mineCnt++
    }
    return mineCnt
}


fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {
    val startX = click[0]
    val startY = click[1]

    rowSize = board.size
    colSize = board[0].size

    when (board[startX][startY]) {
        'M' -> {
            board[startX][startY] = 'X'
            return board
        }
        'E' -> {
            when (val cnt = getMineCount(board, startX, startY)) {
                0 -> board[startX][startY] = 'B'
                else -> {
                    val cntStr = cnt.toString()
                    board[startX][startY] = cntStr[0]
                    return board
                }
            }
        }
    }

    val q: Queue<Pair<Int, Int>> = LinkedList()
    q.add(Pair(startX, startY))

    while (!q.isEmpty()) {
        var curX: Int
        var curY: Int
        q.poll().apply {
            curX = first
            curY = second
        }

        repeat(MOVE_CNT) {
            val nextX = curX + dx[it]
            val nextY = curY + dy[it]

            if (isBoundary(nextX,nextY) && board[nextX][nextY] == 'E') {
                when (val cnt = getMineCount(board, nextX, nextY)) {
                    0 -> {
                        board[nextX][nextY] = 'B'
                        q.add(Pair(nextX, nextY))
                    }
                    else -> {
                        val cntStr: String = cnt.toString()
                        board[nextX][nextY] = cntStr[0]
                    }
                }
            }
        }
    }

    return board
}

fun main(): Unit = with(Scanner(System.`in`)) {
    //TestCase 1
    val board: Array<CharArray> =
            arrayOf(
                    charArrayOf('E', 'E', 'E', 'E', 'E'),
                    charArrayOf('E', 'E', 'M', 'E', 'E'),
                    charArrayOf('E', 'E', 'E', 'E', 'E'),
                    charArrayOf('E', 'E', 'E', 'E', 'E')
            )
    val click: IntArray = intArrayOf(3, 0)

    log(updateBoard(board, click))

    val board2: Array<CharArray> =
            arrayOf(
                    charArrayOf('B', '1', 'E', '1', 'B'),
                    charArrayOf('B', '1', 'M', '1', 'B'),
                    charArrayOf('B', '1', '1', '1', 'B'),
                    charArrayOf('B', 'B', 'B', 'B', 'B')
            )
    val click2: IntArray = intArrayOf(1, 2)
    log(updateBoard(board2, click2))

    val board3: Array<CharArray> =
            arrayOf(
                    charArrayOf('E')
            )
    val click3: IntArray = intArrayOf(0, 0)
    log(updateBoard(board3, click3))
}