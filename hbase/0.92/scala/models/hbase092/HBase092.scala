/*
 * Copyright 2012 Sentric. See LICENSE for details.
 */

package models.hbase092

import scala.collection.JavaConversions._

class HBase092 extends models.HBase {
  override def eachRegionServer(functionBlock: (models.RegionServer) => Unit) = {
    withAdmin { hbaseAdmin =>
      val status = hbaseAdmin.getClusterStatus()
      val servers = status.getServers()
      servers.foreach { serverName =>
        functionBlock(new RegionServer092(status, serverName))
      }
    }
  }

  override val logFileParser = new LogFileParser092
}
