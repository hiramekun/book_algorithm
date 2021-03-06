package chap11.practice

case class UnionFind(n: Int, size: Array[Int], parent: Array[Int], rank: Array[Int]) {
  var groupCount: Int = n

  /**
    * @return root element
    */
  def find(x: Int): Int = {
    if (parent(x) == x) x
    else {
      parent(x) = find(parent(x))
      parent(x)
    }
  }

  /**
    * @return the size of the set x belongs to
    */
  def calcSize(x: Int): Int = size(find(x))

  /**
    * unite sets which x belongs and y belongs
    */
  def unite(x: Int, y: Int): Unit = {
    val findX = find(x)
    val findY = find(y)
    if (findX == findY) return
    groupCount -= 1
    if (rank(findX) < rank(findY)) {
      parent(findX) = findY
    } else {
      parent(findY) = findX
      if (rank(findX) == rank(findY)) rank(x) += 1
    }
    size(findX) = size(findX) + size(findY)
    size(findY) = size(findX)
  }

  /**
    * @return if x and y belong to the same set
    */
  def isSame(x: Int, y: Int): Boolean = find(x) == find(y)
}

object UnionFind {
  def apply(n: Int): UnionFind = {
    UnionFind(
      n = n,
      size = Array.fill(n)(1),
      parent = (0 until n).toArray,
      rank = Array.fill(n)(0)
    )
  }
}
