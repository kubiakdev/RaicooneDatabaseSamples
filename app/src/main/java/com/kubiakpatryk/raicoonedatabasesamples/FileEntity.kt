package com.kubiakpatryk.raicoonedatabasesamples

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class FileEntity(@Id var id : Long,
                      var name : String,
                      var type: String,
                      var extension: String,
                      var parent : String,
                      var disc : String,
                      var url : String,
                      var createdDate : String,
                      var modifiedDate : String)