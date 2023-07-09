// Définition d'une cellule
interface Cell {
    id: number;
    resources: number;
    neighbors: number[];
  }
  
  // Définition de la carte
  const map: Cell[] = [
    { id: 1, resources: 1, neighbors: [2, 3, 4] },
    { id: 2, resources: 2, neighbors: [1, 3] },
    { id: 3, resources: 1, neighbors: [1, 2, 4] },
    { id: 4, resources: 2, neighbors: [1, 3] },
    // ...
  ];
  
  // Fonction pour trouver le voisin le plus proche avec des ressources du type spécifié
  function findNearestNeighborWithResources(startCellId: number, resourceType: number): number | null {
    // Initialisation des structures de données nécessaires
    const openSet: number[] = [startCellId];
    const visited: Set<number> = new Set();
    const distance: Map<number, number> = new Map();
    distance.set(startCellId, 0);
  
    while (openSet.length > 0) {
      // Recherche de la cellule avec la distance minimale
      let currentCellId: number | undefined;
      let currentDistance: number | undefined;
      for (const cellId of openSet) {
        const cellDistance = distance.get(cellId);
        if (cellDistance !== undefined && (currentDistance === undefined || cellDistance < currentDistance)) {
          currentCellId = cellId;
          currentDistance = cellDistance;
        }
      }
  
      if (currentCellId === undefined) break;
  
      // Vérification si la cellule courante contient les ressources recherchées
      const currentCell = map.find(cell => cell.id === currentCellId);
      if (currentCell && currentCell.resources === resourceType) {
        return currentCellId;
      }
  
      // Ajout de la cellule courante aux cellules visitées
      visited.add(currentCellId);
  
      // Parcours des voisins de la cellule courante
      for (const neighborId of currentCell?.neighbors || []) {
        if (!visited.has(neighborId)) {
          // Calcul de la nouvelle distance
          const neighborDistance = (distance.get(currentCellId) || 0) + 1;
  
          if (!distance.has(neighborId) || neighborDistance < (distance.get(neighborId) || 0)) {
            // Mise à jour de la distance
            distance.set(neighborId, neighborDistance);
  
            // Ajout du voisin à l'ensemble des cellules à explorer
            if (!openSet.includes(neighborId)) {
              openSet.push(neighborId);
            }
          }
        }
      }
    }
  
    // Aucun voisin contenant des ressources du type spécifié n'a été trouvé
    return null;
  }
  
  // Exemple d'utilisation
  const startCellId = 1;
  const resourceType = 2;
  const nearestNeighborId = findNearestNeighborWithResources(startCellId, resourceType);
  
  if (nearestNeighborId !== null) {
    console.log(`Le voisin le plus proche contenant des ressources de type ${resourceType} est la cellule ${nearestNeighborId}.`);
  } else {
    console.log("error");
    
  }  