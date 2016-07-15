package com.ttnd.linksharing

class SecurityFilters {

    def filters = {
        all(controller:'user|topic|resource', action:'dashboard|save|delete') {
            before = {
                if (!session.user ) {
                    redirect(controller: 'login', action: 'index')
                    return false
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
