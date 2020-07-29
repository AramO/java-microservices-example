var cfg = {
    _id : 'rs0',
    members: [
      { _id : 0, host : "mongoserver0:27017" },
      { _id : 1, host : "mongoserver1:27017" },
      { _id : 2, host : "mongoserver2:27017",  arbiterOnly: true }
    ]
  };

rs.initiate();

cfg.protocolVersion = 1;
rs.reconfig(cfg, { force: true });