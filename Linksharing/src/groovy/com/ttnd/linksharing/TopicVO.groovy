package com.ttnd.linksharing

/**
 * Created by ttnd on 7/7/16.
 */
class TopicVO {
    Long id
    String name
    Visibility visibility
    Long count
    User createdBy
    Long subscriptionCount
    List<Subscription> subscriptions
    
}
