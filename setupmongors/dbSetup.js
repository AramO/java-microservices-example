//db.grantRolesToUser(
//   "admin",
//   [ "clusterManager" ]
//)

db.getSiblingDB('admin').createUser({
  user: 'mongoUser',
  pwd: 'password',
  roles: [{ role: 'root', db: 'admin' }]
});