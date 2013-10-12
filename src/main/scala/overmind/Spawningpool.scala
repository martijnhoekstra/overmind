/*
This file is part of Overmind.

Overmind is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Overmind is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Overmind.  If not, see <http://www.gnu.org/licenses/>.
*/

package overmind

import akka.actor.ActorSystem
import akka.actor.Props

object Spawningpool {

  def main(args: Array[String]) {
    val l = args.toList
    l match {
      case Nil => wakenOvermind
      case "hive" :: address :: Nil => wakenHive(address)
      case _ => printHelp
    }
  }
  
  def printHelp {
    println("you're doing it wrong")
  }
  
  def wakenHive(address : String) {
    println(s"starting a hive for $address")
    val hivesystem = ActorSystem("hivesystem")
    val queen = hivesystem.actorOf(Queen(address), "queen")
    queen ! "awaken"
  }
  
  def wakenOvermind {
    val overmindsystem = ActorSystem("overmindsystem")
    val overmind = overmindsystem.actorOf(Props[Overmind], "overmind")
    overmind ! "showoff"
  }
  

  
  
}