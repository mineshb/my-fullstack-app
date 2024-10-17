import { UserComponent } from './user/user.component'; // Adjust path as necessary

const routes: Routes = [
  { path: 'users', component: UserComponent },
  // other routes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
