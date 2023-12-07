using Microsoft.AspNetCore.Mvc;

namespace Controllers
{
    [ApiController]
    [Route("[controller]")]

    public class VoitureController : ControllerBase
    {

        [HttpGet(Name = "Voitures")]
        public IEnumerable<Voiture> Get()
        {
            return null;
        }

        [HttpPost(Name = "Voitures")]
        public void Post()
        {

        }

        [HttpPut(Name = "Voitures")]
        public void Put(string id)
        {
        
        }

        [HttpDelete(Name = "Voitures")]
        public void Delete(string id)
        {
        
        }

    }

}
