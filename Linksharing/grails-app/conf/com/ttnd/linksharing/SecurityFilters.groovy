package com.ttnd.linksharing

class SecurityFilters {

    def filters = {
        all(controller:'*', action:'*',invert:'true') {
            before = {
                if (!session.user ) {
                    redirect(action: 'login')
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
