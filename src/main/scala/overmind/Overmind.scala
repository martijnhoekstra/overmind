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

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.PoisonPill

class Overmind extends Actor {
  val address : String = context.parent.path.root.address.hostPort
  def receive = {
    case "showoff" => {
      spawnHive(address)
      spawnHive(address)
    }
    case "hiveready" => {
      sender ! "spawn"
    }
    case "minionready" => {
      sender ! "Dance for my amusement!"
    }
    case Compliance(action) => {
      println(s"hahaha! my minion complied with my command $action")
      sender ! PoisonPill
    }
  }

  def spawnHive(address: String) {
    println("attempting to run a process...")
    //LocalRunner.run(Daemon, "start", "port")
    GridRunner.run(Daemon, "target/start", address)
  }

}