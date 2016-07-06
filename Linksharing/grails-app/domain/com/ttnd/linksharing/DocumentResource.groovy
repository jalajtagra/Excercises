package com.ttnd.linksharing

class DocumentResource extends Resource{

    String filePath
    static mapping = {
        discriminator("Document")
    }

    @Override
    String toString() {
        return filePath
    }
}
