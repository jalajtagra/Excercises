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


}
