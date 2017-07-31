package service

//import java.util.Locale.Category

import module.{Price, Item, Inventory}

import scala.concurrent.Future

trait InventoryServies {

  def priceInfo(inventory: Inventory, id : Int): Future[Price] = Future{

    inventory.items.filter(_.id == id)(0).price

  }


  def sortByPrice(inventory: Inventory, filterBy : String): Future[Inventory] = Future{

    val filteredList = inventory.items.sortBy(_.price.cost)

    if(filterBy.equalsIgnoreCase("LowTOHigh")) {

      Inventory(filteredList)

    }

    else Inventory(filteredList.reverse)

  }

  def findItem(inventory: Inventory, itemType: String, itemName: String, filter : Option[String]) = {

    val searchList = inventory.items.filter(_.itemType==itemType).filter(_.name==itemName)

    if(searchList.isEmpty) throw new NoSuchElementException
    else {

      filter match {

        case Some(elem) => sortByPrice(Inventory(searchList),elem)
        case None => searchList

      }

    }

  }

  def updateItemCount(inventory: Inventory, item: Item, update : Int, f:(Int,Int)=>Int) :Future[Option[Item]] = Future {

        val itemWithOldCount = inventory.items.filter(_.itemType == item)(0)

        if (update == 0) {
          val itemNewCount = itemWithOldCount.copy(noOfItems = f(itemWithOldCount.noOfItems, update))
          Some(itemNewCount)

        }
        else None
      }

}






















