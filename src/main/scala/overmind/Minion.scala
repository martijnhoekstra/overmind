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
import akka.actor.ActorSelection
import akka.actor.Props

object Minion {
  def apply(overmind : ActorSelection) = Props(classOf[Queen], overmind)
}

class Minion(overmind : ActorSelection) extends Actor {
  overmind ! "minionready"
  def receive = {
    case s : String => overmind ! Compliance(s)
  }
}

case class Compliance(command : String)