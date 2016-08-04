package com.ttnd.linksharing

class LinkResource extends Resource {

    String url
    static mapping = {
        discriminator("Link")
    }

    @Override
    String toString() {
        return url;
    }

    def afterInsert(){
        Set<Subscription> subscriptions = topic.subscriptions
        subscriptions?.each{
            ReadingItem readingItem = new ReadingItem(resource: this,user:it.user,isRead: true)
            if(readingItem.user == this.createdBy){
                readingItem.isRead = true
                this.addToReadingItems(readingItem)
            }else{


                    this.addToReadingItems(readingItem)

            }

        }
    }



}
