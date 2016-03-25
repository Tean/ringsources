import java.net.InetSocketAddress
import java.nio.channels.ServerSocketChannel

import org.junit.Test

/**
  * Created by tanshengyong on 16/3/16.
  */
class TestCase {
  @Test
  def test(): Unit = {
    val st = System.currentTimeMillis()
    (1 to 1000).foreach(
      _ => println(_)
    )
    val ed = System.currentTimeMillis()
    println(ed-st)
  }

  def myfunc(param: String) {
    print(param)
  }

  def serve(port: Int): Unit = {
    val server = ServerSocketChannel.open().bind(new InetSocketAddress(port))
  }
}
